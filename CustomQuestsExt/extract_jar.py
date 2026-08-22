#!/usr/bin/env python3
"""
Script to extract and decompile GiranForgeInterface.ext.jar
Usage: python3 extract_jar.py
"""

import os
import sys
import zipfile
import shutil
import subprocess
from pathlib import Path
import urllib.request

# Configuration
JAR_FILE = "decompiled/GiranForgeInterface.ext.jar"
OUTPUT_DIR = "decompiled/extracted"
TEMP_DIR = "decompiled/temp_classes"
TOOLS_DIR = "tools"


class Colors:
    GREEN = '\033[0;32m'
    YELLOW = '\033[1;33m'
    RED = '\033[0;31m'
    BLUE = '\033[0;34m'
    NC = '\033[0m'  # No Color


def print_colored(message, color):
    """Print colored message"""
    print(f"{color}{message}{Colors.NC}")


def check_java():
    """Check if Java is installed"""
    try:
        result = subprocess.run(['java', '-version'], 
                              capture_output=True, 
                              text=True)
        return result.returncode == 0
    except FileNotFoundError:
        return False


def download_cfr():
    """Download CFR decompiler"""
    print_colored("Downloading CFR decompiler...", Colors.YELLOW)
    
    os.makedirs(TOOLS_DIR, exist_ok=True)
    cfr_path = os.path.join(TOOLS_DIR, "cfr.jar")
    
    # CFR download URL (latest version)
    url = "https://github.com/leibnitz27/cfr/releases/download/0.152/cfr-0.152.jar"
    
    try:
        urllib.request.urlretrieve(url, cfr_path)
        print_colored(f"CFR downloaded to {cfr_path}", Colors.GREEN)
        return cfr_path
    except Exception as e:
        print_colored(f"Failed to download CFR: {e}", Colors.RED)
        return None


def find_cfr():
    """Find CFR decompiler"""
    possible_locations = [
        os.path.join(TOOLS_DIR, "cfr.jar"),
        "/usr/local/bin/cfr.jar",
        os.path.expanduser("~/bin/cfr.jar"),
    ]
    
    for location in possible_locations:
        if os.path.isfile(location):
            return location
    
    return None


def extract_jar(jar_path, output_path):
    """Extract JAR file contents"""
    print_colored(f"Extracting {jar_path}...", Colors.YELLOW)
    
    try:
        with zipfile.ZipFile(jar_path, 'r') as jar:
            jar.extractall(output_path)
        
        file_count = sum(1 for _ in Path(output_path).rglob('*') if _.is_file())
        print_colored(f"Extracted {file_count} files", Colors.GREEN)
        return True
    except Exception as e:
        print_colored(f"Error extracting JAR: {e}", Colors.RED)
        return False


def count_files(directory, extension):
    """Count files with specific extension"""
    if not os.path.exists(directory):
        return 0
    return len(list(Path(directory).rglob(f'*{extension}')))


def decompile_with_cfr(cfr_jar, jar_file, output_dir):
    """Decompile .class files using CFR"""
    print_colored("Decompiling .class files with CFR...", Colors.YELLOW)
    
    try:
        # Run CFR decompiler on the JAR file directly
        cmd = [
            'java', '-jar', cfr_jar,
            jar_file,
            '--outputdir', output_dir
        ]
        
        result = subprocess.run(cmd, capture_output=True, text=True)
        
        if result.returncode == 0:
            print_colored("Decompilation completed successfully!", Colors.GREEN)
            return True
        else:
            print_colored(f"Decompilation failed: {result.stderr}", Colors.RED)
            return False
    except Exception as e:
        print_colored(f"Error during decompilation: {e}", Colors.RED)
        return False


def copy_resources(src_dir, dst_dir):
    """Copy non-.class files (resources)"""
    print_colored("Copying resource files...", Colors.YELLOW)
    
    copied = 0
    for root, dirs, files in os.walk(src_dir):
        for file in files:
            if not file.endswith('.class'):
                src_path = os.path.join(root, file)
                rel_path = os.path.relpath(src_path, src_dir)
                dst_path = os.path.join(dst_dir, rel_path)
                
                os.makedirs(os.path.dirname(dst_path), exist_ok=True)
                shutil.copy2(src_path, dst_path)
                copied += 1
    
    if copied > 0:
        print_colored(f"Copied {copied} resource files", Colors.GREEN)


def list_directory_structure(directory, max_depth=3):
    """List directory structure"""
    print_colored("\nExtracted structure:", Colors.YELLOW)
    
    items = []
    for root, dirs, files in os.walk(directory):
        level = root.replace(directory, '').count(os.sep)
        if level < max_depth:
            indent = ' ' * 2 * level
            print(f"{indent}{os.path.basename(root)}/")
            sub_indent = ' ' * 2 * (level + 1)
            for file in files[:10]:  # Limit files shown per directory
                print(f"{sub_indent}{file}")
            if len(files) > 10:
                print(f"{sub_indent}... and {len(files) - 10} more files")


def main():
    """Main execution function"""
    print_colored("=" * 50, Colors.GREEN)
    print_colored("JAR Extraction and Decompilation Script", Colors.GREEN)
    print_colored("=" * 50, Colors.GREEN)
    
    # Check if JAR file exists
    if not os.path.isfile(JAR_FILE):
        print_colored(f"Error: JAR file not found at {JAR_FILE}", Colors.RED)
        sys.exit(1)
    
    print_colored(f"Found JAR file: {JAR_FILE}", Colors.GREEN)
    
    # Create directories
    print_colored("Creating directories...", Colors.YELLOW)
    os.makedirs(OUTPUT_DIR, exist_ok=True)
    os.makedirs(TEMP_DIR, exist_ok=True)
    
    # Extract JAR
    if not extract_jar(JAR_FILE, TEMP_DIR):
        sys.exit(1)
    
    # Count .class files
    class_count = count_files(TEMP_DIR, '.class')
    print_colored(f"Found {class_count} .class files", Colors.GREEN)
    
    if class_count == 0:
        print_colored("No .class files found. Copying all contents...", Colors.YELLOW)
        shutil.copytree(TEMP_DIR, OUTPUT_DIR, dirs_exist_ok=True)
    else:
        # Check for Java
        if not check_java():
            print_colored("Java is not installed. Cannot decompile .class files.", Colors.RED)
            print_colored("Copying .class files without decompilation...", Colors.YELLOW)
            shutil.copytree(TEMP_DIR, OUTPUT_DIR, dirs_exist_ok=True)
        else:
            # Find or download CFR
            cfr_jar = find_cfr()
            
            if not cfr_jar:
                print_colored("CFR decompiler not found.", Colors.YELLOW)
                cfr_jar = download_cfr()
            else:
                print_colored(f"Found CFR at: {cfr_jar}", Colors.GREEN)
            
            if cfr_jar:
                # Decompile with CFR
                if decompile_with_cfr(cfr_jar, JAR_FILE, OUTPUT_DIR):
                    # Copy resources
                    copy_resources(TEMP_DIR, OUTPUT_DIR)
                else:
                    print_colored("Decompilation failed. Copying .class files...", Colors.YELLOW)
                    shutil.copytree(TEMP_DIR, OUTPUT_DIR, dirs_exist_ok=True)
            else:
                print_colored("Copying .class files without decompilation...", Colors.YELLOW)
                shutil.copytree(TEMP_DIR, OUTPUT_DIR, dirs_exist_ok=True)
    
    # Cleanup
    print_colored("Cleaning up temporary files...", Colors.YELLOW)
    shutil.rmtree(TEMP_DIR, ignore_errors=True)
    
    # Summary
    print_colored("\n" + "=" * 50, Colors.GREEN)
    print_colored("Extraction completed!", Colors.GREEN)
    print_colored(f"Output directory: {OUTPUT_DIR}", Colors.GREEN)
    
    java_count = count_files(OUTPUT_DIR, '.java')
    class_count = count_files(OUTPUT_DIR, '.class')
    
    print_colored(f"Java files: {java_count}", Colors.GREEN)
    print_colored(f"Class files: {class_count}", Colors.GREEN)
    
    # List structure
    if os.path.exists(OUTPUT_DIR):
        list_directory_structure(OUTPUT_DIR)
    
    print_colored("=" * 50, Colors.GREEN)


if __name__ == "__main__":
    main()
