class Pessoa:
    def __init__(self, nome, idade, estado):
        self.nome = nome
        self.idade = idade
        self.estado = estado

    @property
    def estado_getter(self):
        if self.estado == "tuff":
            return f'Estado: {self.estado}. Parabéns!'
        else:
            return f'Estado: {self.estado}.'
print(f'Digite seu nome:')
nome = input()
print(f'Digite sua idade')
idade = int(input())
print(f'Qual sua aura:')
aura = int(input())

if aura >= 8000:
    estado = "tuff"
elif aura >= 5000:
    estado = "based"
elif aura >= 2000:
    estado = "normie"
else:
    estado = "beta"

pessoa = Pessoa(nome, idade, estado)

print(f'Nome: {pessoa.nome}')
print(f'idade: {pessoa.idade}')

print(pessoa.estado_getter)

for i in range(1000):







S