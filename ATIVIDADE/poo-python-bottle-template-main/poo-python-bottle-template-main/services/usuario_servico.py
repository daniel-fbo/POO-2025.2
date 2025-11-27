from bottle import request
from models.usuario import UserModel, User, Livro

class UserService:
    def __init__(self):
        self.user_model = UserModel()

    def get_all(self):
        return self.user_model.get_all()

    def save(self):
        last_id = max([u.id for u in self.user_model.get_all()], default=0)
        new_id = last_id + 1

        name = request.forms.get("name")
        email = request.forms.get("email")

        user = User(id=new_id, name=name, email=email, livros=[])
        self.user_model.add_user(user)

    def get_by_id(self, user_id):
        return self.user_model.get_by_id(user_id)

    def add_book(self, user_id):
        user = self.get_by_id(user_id)

        if not user:
            return None

        titulo = request.forms.get("titulo")
        autor = request.forms.get("autor")

        livro = Livro(titulo, autor)
        user.adicionaLivro(livro)

        self.user_model.update_user(user)

    def remove_book(self, user_id, titulo):
        user = self.get_by_id(user_id)

        if not user:
            return None

        user.removeLivro(titulo)
        self.user_model.update_user(user)

    def delete_user(self, user_id):
        self.user_model.delete_user(user_id)
