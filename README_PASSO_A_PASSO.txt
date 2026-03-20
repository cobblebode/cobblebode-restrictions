COBBLEBODE RESTRICTIONS - PASSO A PASSO MAIS FÁCIL

OBJETIVO
- Compilar online no GitHub
- Baixar o .jar pronto
- Colocar no servidor

O QUE ESTE MOD FAZ
- Bloqueia o uso destes itens no DITTO:
  goldencap
  atksilvercap
  spatksilvercap
  spdefsilvercap
  speedsilvercap
  defsilvercap
  hpsilvercap
  shiningatksilvercap
  shiningspatksilvercap
  shiningspdefsilvercap
  shiningspeedsilvercap
  shiningdefsilvercap
  shininghpsilvercap
  woodencap

MENSAGEM
- "Não é possível usar esse item nesse pokémon."

-----------------------------------
PARTE 1 - CRIAR O REPOSITÓRIO NO GITHUB
-----------------------------------

1. Crie uma conta no GitHub, se ainda não tiver.
2. Clique em "New repository".
3. Nome sugerido:
   cobblebode-restrictions
4. Pode deixar público ou privado.
5. Clique em "Create repository".

-----------------------------------
PARTE 2 - ENVIAR ESTES ARQUIVOS
-----------------------------------

1. Extraia este zip no seu computador.
2. Entre no repositório que você criou no GitHub.
3. Clique em "Add file" > "Upload files".
4. Arraste TODOS os arquivos extraídos para lá.
5. Espere subir tudo.
6. Clique em "Commit changes".

IMPORTANTE
- O arquivo build.gradle precisa ficar na raiz do repositório.
- A pasta src também precisa ficar na raiz.
- A pasta .github também precisa ficar na raiz.

-----------------------------------
PARTE 3 - COMPILAR ONLINE
-----------------------------------

1. No GitHub, clique na aba "Actions".
2. Você vai ver o workflow "Build Mod".
3. Clique nele.
4. Clique em "Run workflow".
5. Espere terminar.

Se der certo:
- vai aparecer uma bolinha verde
- a build ficará como "successful"

-----------------------------------
PARTE 4 - BAIXAR O .JAR
-----------------------------------

1. Abra a build concluída.
2. Role a página até "Artifacts".
3. Clique em "cobblebode-restrictions-jar".
4. Baixe o zip do artifact.
5. Extraia.
6. O .jar estará dentro dele.

-----------------------------------
PARTE 5 - INSTALAR NO SERVIDOR
-----------------------------------

1. Pegue o .jar gerado.
2. Coloque em:
   /mods
3. Reinicie o servidor.

-----------------------------------
TESTE NO JOGO
-----------------------------------

1. Pegue um Ditto.
2. Tente usar um dos caps nele.
3. O uso deve ser bloqueado.
4. A mensagem deve aparecer no chat.

-----------------------------------
SE DER ERRO NA BUILD
-----------------------------------

Me mande:
- print da tela do Actions
OU
- o texto vermelho da falha

que eu ajusto.
