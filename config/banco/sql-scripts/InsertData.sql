
USE modelo;

INSERT INTO tb_perfis (codigo, nome, registro, situacao) VALUES (1, 'Usuário comum', '2018-10-18 00:00:00.000', 1);
INSERT INTO tb_perfis (codigo, nome, registro, situacao) VALUES (2, 'Usuário master', '2018-10-18 00:00:00.000', 1);
INSERT INTO tb_perfis (codigo, nome, registro, situacao) VALUES (3, 'Usuário master of puppets', '2018-10-18 00:00:00.000', 1);

INSERT INTO tb_permissoes (codigo, descricao, registro, situacao) VALUES ('CADASTRAR_USUARIO', 'Cadastro de usuário', '2018-10-18 00:00:00.000', 1);
INSERT INTO tb_permissoes (codigo, descricao, registro, situacao) VALUES ('LISTAR_USUARIOS', 'Listagem de usuário', '2018-10-18 00:00:00.000', 1);

INSERT INTO tb_permissoes_perfil(perfil, permissao) VALUES (2, 'CADASTRAR_USUARIO');
INSERT INTO tb_permissoes_perfil(perfil, permissao) VALUES (2, 'LISTAR_USUARIOS');
INSERT INTO tb_permissoes_perfil(perfil, permissao) VALUES (3, 'CADASTRAR_USUARIO');
INSERT INTO tb_permissoes_perfil(perfil, permissao) VALUES (3, 'LISTAR_USUARIOS');

