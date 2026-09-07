#!/usr/bin/env python3
"""Package only the Guard overlay, never DB config, credentials or private signing keys."""
import argparse
import hashlib
import json
from pathlib import Path
import subprocess
import zipfile

ROOT = Path(__file__).resolve().parent
def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--server-worktree', type=Path, required=True)
    parser.add_argument('--output', type=Path, required=True)
    args = parser.parse_args()
    server, output = args.server_worktree.resolve(), args.output.resolve()
    if output.exists() or output.is_relative_to(server) or output.is_relative_to(ROOT):
        raise SystemExit('Choose a new archive outside the input worktrees')
    files = {
        'gameserver/HorizonGuard.ext.jar': ROOT / 'dist/HorizonGuard.ext.jar',
        'authserver/AuthGuardHardening.ext.jar': ROOT / 'dist/AuthGuardHardening.ext.jar',
    }
    for name in ('gameserver/GameServer_loop.sh', 'gameserver/StartGameServer.bat',
                 'authserver/AuthServer_loop.sh', 'authserver/StartAuthServer.bat',
                 'authserver/config/authguard_file_hashes.xml', 'authserver/config/authguard_file_hashes.dtd',
                 'gameserver/config/guard.properties', 'gameserver/config/guard/release-public.blob',
                 'tools/guard/WDROZENIE.md', 'tools/guard/DDOS.md', 'tools/guard/nginx-location.conf.example'):
        files[name] = server / name
    for manifest in sorted((server / 'gameserver/config/guard/releases').glob('*.manifest')):
        files[manifest.relative_to(server).as_posix()] = manifest
    if not any(n.endswith('.manifest') for n in files): raise SystemExit('Missing signed release')
    payloads = {name: path.read_bytes() for name, path in files.items()}
    def commit(path): return subprocess.check_output(['git', '-C', str(path), 'rev-parse', 'HEAD'], text=True).strip()
    metadata = {'schema': 1, 'mode': 'audit', 'publicEndpointConfigured': False,
                'extensionCommit': commit(ROOT), 'serverCommit': commit(server),
                'files': {name: hashlib.sha256(body).hexdigest() for name, body in payloads.items()}}
    output.parent.mkdir(parents=True, exist_ok=True)
    with zipfile.ZipFile(output, 'x', zipfile.ZIP_DEFLATED) as archive:
        payloads['SERVER-RELEASE.json'] = (json.dumps(metadata, indent=2) + '\n').encode()
        for name, body in sorted(payloads.items()):
            info = zipfile.ZipInfo(name, (1980, 1, 1, 0, 0, 0)); info.compress_type = zipfile.ZIP_DEFLATED
            info.external_attr = (0o100755 if name.endswith('.sh') else 0o100644) << 16
            archive.writestr(info, body)
    print(output.name, output.stat().st_size, hashlib.sha256(output.read_bytes()).hexdigest())

if __name__ == '__main__': main()
