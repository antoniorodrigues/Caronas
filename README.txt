- Resumo do projeto:

	O sistema consiste em uma rede social na web para compartilhamento de caronas.
	Os usuários solicitam ou divulgam uma carona, e o sistema ajuda a pareá-los.
	O sistema facilitará que motoristas e caroneiros encontrem as melhores opções
	para uma carona, e ajudará na negociação envolvida em solicitar uma carona, 
	do ponto de vista dos caroneiros, e em escolher os melhores caroneiros, do 
	ponto de vista dos motoristas.
	Melhores caroneiros podem ser definidos por vários critérios, como local de
	residência, reputação, ou conhecimento prévio.


- Estrutura do projeto:

	A estrutura do sistema está organizada da seguinte forma:
		- O código Java de todo o projeto está distribuído em 6 pacotes:
			> controller: possui a classe que é responsável por ser a controladora
						  das requisições do sistema. 
			> excecoes: possui todas as exceções usadas pelas diversas classes do
						sistema.
			> funcionalidades: possui todas as funcionalidades do sistema, como por
							   exemplo, as classes responsáveis por criar os usuários, 
							   as caronas e etc.
			> gerenciadores: possui as classes que gereciam os dados do sistema e
							 também as caronas.
			> testes: possui as classes de teste de unidade.
			> testesdeaceitacao: possui a classe que testa o sistema.
			
	
	Há também um diretório de nome 'scripts', o qual contém todos os scripts de testes
	de aceitação do projeto.
	
	Na raiz do projeto ainda tem o arquivo pom.xml, o easyaccept.jar e também o diagrama
	UML do sistema, ou pelo menos da parte mais relevante de se mostrar.