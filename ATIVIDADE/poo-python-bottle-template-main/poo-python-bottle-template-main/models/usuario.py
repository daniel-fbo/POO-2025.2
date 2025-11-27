import json
import os
from typing import List

DATA_DIR = os.path.join(os.path.dirname(__file__), '..', 'data')
os.makedirs(DATA_DIR, exist_ok=True)

class Livro:
    def __init__(self, titulo, autor):
        self.titulo = titulo
        self.autor = autor

    def to_dict(self):
        return {
            "titulo": self.titulo,
            "autor": self.autor
        }

    @classmethod
    def from_dict(cls, data):
        return cls(
            titulo=data["titulo"],
            autor=data["autor"]
        )

class User:
    def __init__(self, id, name, email, livros=None):
        self.id = id
        self.name = name
        self.email = email
        self.livros = livros if livros is not None else []  # <- ARRUMADO

    def adicionaLivro(self, livro: Livro):
        self.livros.append(livro)

    def removeLivro(self, titulo):
        self.livros = [l for l in self.livros if l.titulo != titulo]

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "email": self.email,
            "livros": [l.to_dict() for l in self.livros]
        }

    @classmethod
    def from_dict(cls, data):
        livros = [Livro.from_dict(d) for d in data.get("livros", [])]
        return cls(
            id=data["id"],
            name=data["name"],
            email=data["email"],
            livros=livros
        )
    
class UserModel:
    FILE_PATH = os.path.join(DATA_DIR, 'users.json')

    def __init__(self):
        self.users = self._load()


    def _load(self):
        if not os.path.exists(self.FILE_PATH):
            return []
        with open(self.FILE_PATH, 'r', encoding='utf-8') as f:
            data = json.load(f)
            return [User.from_dict(item) for item in data]


    def _save(self):
        with open(self.FILE_PATH, 'w', encoding='utf-8') as f:
            json.dump([u.to_dict() for u in self.users], f, indent=4, ensure_ascii=False)


    def get_all(self):
        return self.users


    def get_by_id(self, user_id: int):
        return next((u for u in self.users if u.id == user_id), None)


    def add_user(self, user: User):
        self.users.append(user)
        self._save()


    def update_user(self, updated_user: User):
        for i, user in enumerate(self.users):
            if user.id == updated_user.id:
                self.users[i] = updated_user
                self._save()
                break


    def delete_user(self, user_id: int):
        self.users = [u for u in self.users if u.id != user_id]
        self._save()
