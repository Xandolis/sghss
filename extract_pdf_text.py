import pathlib
import PyPDF2

files = [
    pathlib.Path('requisitos/FAQ Backend.pdf'),
    pathlib.Path('requisitos/Orientacoes de Projetos.pdf'),
    pathlib.Path('requisitos/Roteiro de Projetos - 2025A1 rev 0.2.pdf')
]
for f in files:
    print('===', f.name, '===')
    with f.open('rb') as fh:
        reader = PyPDF2.PdfReader(fh)
        for i, p in enumerate(reader.pages[:10]):
            print('--- page', i+1, '---')
            print((p.extract_text() or '').strip())
            print()
