

class Animal:
    def __init__(self, nome=""):
        self.nome = nome

    def comer(self):
        return f'O {self.nome} está comendo...'

class Cachorro(Animal):
    def __init__(self, nome, raca, idade=None):
        super().__init__(nome)
        self.raca = raca
        self.idade = idade

    def get_idade(self):
        return self.idade
    def get_raca(self):
        return self.raca
    def latir(self):
        return f'{self.nome} está latindo!'

    meu_cachorro = Cachorro("Destruidor", "Pinshcer",6)
    print(meu_cachorro.latir())
    print(f'Raça: {meu_cachorro.get_raca()}')
    printf(f'Idade: {meu_cachorro.get_idade()}')