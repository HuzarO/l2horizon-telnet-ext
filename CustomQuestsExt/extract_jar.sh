#!/bin/bash

# Script to extract and decompile GiranForgeInterface.ext.jar
# Usage: ./extract_jar.sh

set -e

# Configuration
JAR_FILE="decompiled/GiranForgeInterface.ext.jar"
OUTPUT_DIR="decompiled/extracted"
TEMP_DIR="decompiled/temp_classes"

# Colors for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${GREEN}Starting extraction of GiranForgeInterface.ext.jar${NC}"

# Check if JAR file exists
if [ ! -f "$JAR_FILE" ]; then
    echo -e "${RED}Error: JAR file not found at $JAR_FILE${NC}"
    exit 1
fi

# Create output directories
echo -e "${YELLOW}Creating output directories...${NC}"
mkdir -p "$OUTPUT_DIR"
mkdir -p "$TEMP_DIR"

# Extract JAR contents
echo -e "${YELLOW}Extracting JAR contents...${NC}"
cd "$TEMP_DIR"
jar xf "../../$JAR_FILE"
cd ../..

# Find and process .class files
echo -e "${YELLOW}Searching for .class files...${NC}"
class_count=$(find "$TEMP_DIR" -name "*.class" | wc -l | tr -d ' ')
echo -e "${GREEN}Found $class_count .class files${NC}"

if [ $class_count -eq 0 ]; then
    echo -e "${YELLOW}No .class files found. Copying all contents...${NC}"
    cp -r "$TEMP_DIR"/* "$OUTPUT_DIR/" 2>/dev/null || true
else
    # Check for available decompiler
    echo -e "${YELLOW}Checking for available decompilers...${NC}"
    
    DECOMPILER=""
    
    # Check for CFR
    if command -v java &> /dev/null; then
        if [ -f "/usr/local/bin/cfr.jar" ] || [ -f "$HOME/bin/cfr.jar" ]; then
            DECOMPILER="cfr"
            echo -e "${GREEN}Found CFR decompiler${NC}"
        fi
    fi
    
    # Check for jd-cli
    if [ -z "$DECOMPILER" ] && command -v jd-cli &> /dev/null; then
        DECOMPILER="jd-cli"
        echo -e "${GREEN}Found jd-cli decompiler${NC}"
    fi
    
    # Check for fernflower
    if [ -z "$DECOMPILER" ] && [ -f "/usr/local/bin/fernflower.jar" ]; then
        DECOMPILER="fernflower"
        echo -e "${GREEN}Found Fernflower decompiler${NC}"
    fi
    
    if [ -z "$DECOMPILER" ]; then
        echo -e "${YELLOW}No decompiler found. Installing CFR...${NC}"
        echo -e "${YELLOW}Downloading CFR decompiler...${NC}"
        
        mkdir -p tools
        curl -L "https://github.com/leibnitz27/cfr/releases/download/0.152/cfr-0.152.jar" -o "tools/cfr.jar"
        
        if [ -f "tools/cfr.jar" ]; then
            # Verify it's a valid jar file
            if jar tf "tools/cfr.jar" > /dev/null 2>&1; then
                DECOMPILER="cfr"
                echo -e "${GREEN}CFR downloaded successfully${NC}"
            else
                echo -e "${RED}Downloaded file is not a valid JAR. Will copy .class files only.${NC}"
                rm -f "tools/cfr.jar"
            fi
        else
            echo -e "${RED}Failed to download CFR. Will copy .class files only.${NC}"
        fi
    fi
    
    # Decompile .class files
    if [ -n "$DECOMPILER" ]; then
        echo -e "${YELLOW}Decompiling with $DECOMPILER...${NC}"
        
        if [ "$DECOMPILER" = "cfr" ]; then
            # Find CFR jar
            CFR_JAR=""
            [ -f "tools/cfr.jar" ] && CFR_JAR="tools/cfr.jar"
            [ -f "/usr/local/bin/cfr.jar" ] && CFR_JAR="/usr/local/bin/cfr.jar"
            [ -f "$HOME/bin/cfr.jar" ] && CFR_JAR="$HOME/bin/cfr.jar"
            
            if [ -n "$CFR_JAR" ]; then
                # Decompile the original JAR file directly
                java -jar "$CFR_JAR" "$JAR_FILE" --outputdir "$OUTPUT_DIR"
                echo -e "${GREEN}Decompilation completed!${NC}"
            fi
        elif [ "$DECOMPILER" = "jd-cli" ]; then
            find "$TEMP_DIR" -name "*.class" | while read -r class_file; do
                relative_path="${class_file#$TEMP_DIR/}"
                output_path="$OUTPUT_DIR/${relative_path%.class}.java"
                mkdir -p "$(dirname "$output_path")"
                jd-cli "$class_file" -od "$(dirname "$output_path")" 2>/dev/null || echo "Failed to decompile $class_file"
            done
            echo -e "${GREEN}Decompilation completed!${NC}"
        elif [ "$DECOMPILER" = "fernflower" ]; then
            java -jar /usr/local/bin/fernflower.jar "$TEMP_DIR" "$OUTPUT_DIR"
            echo -e "${GREEN}Decompilation completed!${NC}"
        fi
    else
        echo -e "${YELLOW}Copying .class files without decompilation...${NC}"
        cp -r "$TEMP_DIR"/* "$OUTPUT_DIR/"
    fi
fi

# Copy any non-.class files (resources, etc.)
echo -e "${YELLOW}Copying resources and other files...${NC}"
find "$TEMP_DIR" -type f ! -name "*.class" -exec cp --parents {} "$OUTPUT_DIR/" 2>/dev/null \; || true

# Cleanup
echo -e "${YELLOW}Cleaning up temporary files...${NC}"
rm -rf "$TEMP_DIR"

# Summary
echo -e "${GREEN}================================${NC}"
echo -e "${GREEN}Extraction completed!${NC}"
echo -e "${GREEN}Output directory: $OUTPUT_DIR${NC}"

if [ -d "$OUTPUT_DIR" ]; then
    java_count=$(find "$OUTPUT_DIR" -name "*.java" 2>/dev/null | wc -l | tr -d ' ')
    class_count=$(find "$OUTPUT_DIR" -name "*.class" 2>/dev/null | wc -l | tr -d ' ')
    
    echo -e "${GREEN}Java files: $java_count${NC}"
    echo -e "${GREEN}Class files: $class_count${NC}"
    
    # List extracted files
    echo -e "\n${YELLOW}Extracted structure:${NC}"
    tree -L 3 "$OUTPUT_DIR" 2>/dev/null || find "$OUTPUT_DIR" -type f | head -20
fi

echo -e "${GREEN}================================${NC}"
