# JAR Extraction Scripts

Two scripts are provided to extract and decompile the `GiranForgeInterface.ext.jar` file.

## Scripts

### 1. Bash Script: `extract_jar.sh`
Compatible with macOS and Linux systems.

**Usage:**
```bash
./extract_jar.sh
```

### 2. Python Script: `extract_jar.py`
More portable, works on any system with Python 3.

**Usage:**
```bash
python3 extract_jar.py
```

## What They Do

Both scripts will:
1. ✅ Extract the JAR file located at `decompiled/GiranForgeInterface.ext.jar`
2. ✅ Look for or download the CFR decompiler (if Java is available)
3. ✅ Decompile all `.class` files to readable `.java` files
4. ✅ Copy all resource files (non-class files)
5. ✅ Output everything to `decompiled/extracted/`
6. ✅ Clean up temporary files
7. ✅ Display a summary of extracted files

## Requirements

### Bash Script
- **bash** (pre-installed on macOS/Linux)
- **jar** command (comes with JDK)
- **Java** (for decompilation)
- **curl** (for downloading CFR if needed)

### Python Script
- **Python 3.6+**
- **Java** (for decompilation, optional)

## Output

The extracted and decompiled files will be in:
```
decompiled/extracted/
```

The scripts will show:
- Number of `.java` files created
- Number of `.class` files (if decompilation fails)
- Directory structure of extracted content

## Decompilation

The scripts will automatically:
- Check if CFR decompiler is installed
- Download CFR if not found (requires internet connection)
- Use CFR to decompile .class files to .java files

If Java is not available or decompilation fails, the scripts will copy the .class files directly.

## Manual Decompilation

If you want to use a different decompiler, you can:

1. **CFR** (Recommended)
   ```bash
   java -jar tools/cfr.jar decompiled/GiranForgeInterface.ext.jar --outputdir decompiled/extracted
   ```

2. **JD-CLI**
   ```bash
   jd-cli decompiled/GiranForgeInterface.ext.jar -od decompiled/extracted
   ```

3. **Fernflower**
   ```bash
   java -jar fernflower.jar decompiled/GiranForgeInterface.ext.jar decompiled/extracted
   ```

## Troubleshooting

### "JAR file not found"
- Make sure `GiranForgeInterface.ext.jar` exists in the `decompiled/` directory

### "Java not found"
- Install Java: `brew install openjdk` (macOS) or download from oracle.com
- The script will still extract files, but won't decompile them

### Permission denied
```bash
chmod +x extract_jar.sh extract_jar.py
```

### Want to re-extract?
Delete the output directory first:
```bash
rm -rf decompiled/extracted
./extract_jar.sh
```

## Example Output

```
================================
Extraction completed!
Output directory: decompiled/extracted
Java files: 45
Class files: 0
================================
```
