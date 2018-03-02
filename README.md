#Desafio Java [Hands On]
##Apresenta��o do desafio
A proposta do desafio � a cria��o de uma API RESTful, para gerir dados das competi��es dos
Jogos Ol�mpicos Tokyo 2020. O objetivo � a cria��o dos seguintes endpoints:
? Cadastro das competi��es que ocorrer�o nos Jogos Ol�mpicos Tokyo 2020.
? Consultas das competi��es cadastradas
Cada competi��o cadastrada deve conter obrigatoriamente as seguintes informa��es:
? Modalidade (Ex: Futebol, Basquete, etc)
? Local
? Data/Hora de in�cio/t�rmino
? Os 2 pa�ses envolvidos na disputa
? Etapa - Deve contemplar as seguintes op��es: Eliminat�rias, Oitavas de Final, Quartas
de Final, Semifinal, Final
As seguintes regras devem ser respeitadas no fluxo de cadastro das competi��es:
? Duas competi��es n�o podem ocorrer no mesmo per�odo, no mesmo local, para a
mesma modalidade. Ex: Se eu tenho uma partida de futebol que com in�cio �s 18:00 e
t�rmino �s 20:00 no Est�dio 1, eu n�o poderia ter outra partida de futebol se iniciando
�s 19:30 nesse mesmo est�dio
? O fluxo de cadastro deve permitir que se forne�a o mesmo valor, para os 2 pa�ses
envolvidos na disputa, apenas se a etapa for Final ou Semifinal. Para as demais etapas,
n�o se deve permitir que se forne�a o mesmo valor.
? A competi��o deve ter a dura��o de no m�nimo 30 minutos.
? Para evitar problemas, a organiza��o das olimp�adas que limitar a no m�ximo 4
competi��es por dia num mesmo local
? Para situa��es de erro, � necess�rio que a resposta da requisi��o seja coerente em
exibir uma mensagem condizente com o erro.
O endpoint de consulta de competi��es cadastradas, deve retornar os resultados ordenados
por Data/Hora de in�cio da competi��o e deve permitir filtrar todas competi��es para uma dada
modalidade. Esse filtro n�o � obrig�torio - se n�o for fornecido todas competi��es devem ser
retornadas