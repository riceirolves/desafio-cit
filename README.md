#Desafio Java [Hands On]
##Apresentação do desafio
A proposta do desafio é a criação de uma API RESTful, para gerir dados das competições dos
Jogos Olímpicos Tokyo 2020. O objetivo é a criação dos seguintes endpoints:
? Cadastro das competições que ocorrerão nos Jogos Olímpicos Tokyo 2020.
? Consultas das competições cadastradas
Cada competição cadastrada deve conter obrigatoriamente as seguintes informações:
? Modalidade (Ex: Futebol, Basquete, etc)
? Local
? Data/Hora de início/término
? Os 2 países envolvidos na disputa
? Etapa - Deve contemplar as seguintes opções: Eliminatórias, Oitavas de Final, Quartas
de Final, Semifinal, Final
As seguintes regras devem ser respeitadas no fluxo de cadastro das competições:
? Duas competições não podem ocorrer no mesmo período, no mesmo local, para a
mesma modalidade. Ex: Se eu tenho uma partida de futebol que com início às 18:00 e
término às 20:00 no Estádio 1, eu não poderia ter outra partida de futebol se iniciando
às 19:30 nesse mesmo estádio
? O fluxo de cadastro deve permitir que se forneça o mesmo valor, para os 2 países
envolvidos na disputa, apenas se a etapa for Final ou Semifinal. Para as demais etapas,
não se deve permitir que se forneça o mesmo valor.
? A competição deve ter a duração de no mínimo 30 minutos.
? Para evitar problemas, a organização das olimpíadas que limitar a no máximo 4
competições por dia num mesmo local
? Para situações de erro, é necessário que a resposta da requisição seja coerente em
exibir uma mensagem condizente com o erro.
O endpoint de consulta de competições cadastradas, deve retornar os resultados ordenados
por Data/Hora de início da competição e deve permitir filtrar todas competições para uma dada
modalidade. Esse filtro não é obrigátorio - se não for fornecido todas competições devem ser
retornadas