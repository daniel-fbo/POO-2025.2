from models.usuario import User, Livro, UserModel

def main():
    # Instancia o model
    user_model = UserModel()

    user1 = User(id=1, name="Rafael", email="rafael@email.com")
    user2 = User(id=2, name="Ana", email="ana@email.com")

    livro1 = Livro(titulo="O Pequeno Príncipe", autor="Antoine de Saint-Exupéry")
    livro2 = Livro(titulo="1984", autor="George Orwell")
    livro3 = Livro(titulo="Dom Quixote", autor="Miguel de Cervantes")

    user1.adicionaLivro(livro1)
    user1.adicionaLivro(livro2)
    user2.adicionaLivro(livro3)

    user_model.add_user(user1)
    user_model.add_user(user2)

    print("📚 Biblioteca completa:")
    for u in user_model.get_all():
        print(f"\nUsuário: {u.name} ({u.email})")
        if not u.livros:
            print("  Nenhum livro cadastrado")
        else:
            for l in u.livros:
                print(f"  - {l.titulo} ({l.autor})")

    print("\nRemovendo '1984' do Rafael...\n")
    user1.removeLivro("1984")
    user_model.update_user(user1)

    print("📚 Biblioteca atualizada:")
    for u in user_model.get_all():
        print(f"\nUsuário: {u.name} ({u.email})")
        if not u.livros:
            print("  Nenhum livro cadastrado")
        else:
            for l in u.livros:
                print(f"  - {l.titulo} ({l.autor})")

if __name__ == "__main__":
    main()
