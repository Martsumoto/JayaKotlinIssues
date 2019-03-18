# Jaya coding challenge

Aplicação que busca issues do repositório kotlin (https://github.com/JetBrains/kotlin), exibe-os numa lista, e exibe os detalhes da issue quando um item da lista é selecionado.

Bibliotecas utilizadas:
 * retrofit: responsável por buscar as issus pela API do github
 * dagger2: injeção de dependência
 * glide: carregar e exibir as imagens dos usuários nos detalhes das issues
 * rxjava: responsável por gerenciar assincronamente as requisições na API do github (pelo retrofit)
 * mockito: para "mockar" objetos para criação dos testes unitários
