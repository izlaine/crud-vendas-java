-- 1) Crie o banco em uma conexão administrativa (se ainda não existir):
-- CREATE DATABASE sistema_vendas;
-- 2) Conecte-se ao banco sistema_vendas e execute o restante deste arquivo.

CREATE TABLE IF NOT EXISTS tb_marca (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL UNIQUE,
    descricao TEXT,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS tb_categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    categoria_id BIGINT NULL REFERENCES tb_categoria(id)
);

CREATE TABLE IF NOT EXISTS tb_produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(180) NOT NULL,
    descricao TEXT,
    preco NUMERIC(15,2) NOT NULL CHECK (preco > 0),
    quantidade_estoque INTEGER NOT NULL CHECK (quantidade_estoque >= 0),
    marca_id BIGINT NOT NULL REFERENCES tb_marca(id),
    categoria_id BIGINT NOT NULL REFERENCES tb_categoria(id),
    caminho_imagem VARCHAR(500)
);

INSERT INTO tb_marca (nome, descricao, ativo)
VALUES ('Nike', 'Marca de exemplo', TRUE), ('Adidas', 'Marca de exemplo', TRUE)
ON CONFLICT (nome) DO NOTHING;

INSERT INTO tb_categoria (nome, categoria_id)
SELECT 'Eletrônicos', NULL
WHERE NOT EXISTS (SELECT 1 FROM tb_categoria WHERE nome = 'Eletrônicos' AND categoria_id IS NULL);

INSERT INTO tb_categoria (nome, categoria_id)
SELECT 'Acessórios', id FROM tb_categoria
WHERE nome = 'Eletrônicos' AND categoria_id IS NULL
AND NOT EXISTS (SELECT 1 FROM tb_categoria WHERE nome = 'Acessórios');

INSERT INTO tb_categoria (nome, categoria_id)
SELECT 'Calçados', NULL
WHERE NOT EXISTS (SELECT 1 FROM tb_categoria WHERE nome = 'Calçados' AND categoria_id IS NULL);

INSERT INTO tb_produto (nome, descricao, preco, quantidade_estoque, marca_id, categoria_id)
SELECT 'Tênis de exemplo', 'Produto inicial para testes do CRUD', 299.90, 10, m.id, c.id
FROM tb_marca m, tb_categoria c
WHERE m.nome = 'Nike' AND c.nome = 'Calçados'
AND NOT EXISTS (SELECT 1 FROM tb_produto WHERE nome = 'Tênis de exemplo');

INSERT INTO tb_produto (nome, descricao, preco, quantidade_estoque, marca_id, categoria_id)
SELECT 'Fone de ouvido', 'Produto inicial para testes do CRUD', 149.90, 20, m.id, c.id
FROM tb_marca m, tb_categoria c
WHERE m.nome = 'Adidas' AND c.nome = 'Acessórios'
AND NOT EXISTS (SELECT 1 FROM tb_produto WHERE nome = 'Fone de ouvido');
