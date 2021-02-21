insert into usuario(nome, email, senha, cargo) values 
('bla','bla@email.com','pass123', 1),
('tiago','tiago@gmail.com','123456', 2),
('aline','aline@gmail.com','654321', 2);

insert into produto(descricao, preco, qt_estoque, qt_estoque_minimo, qt_reservada, usuario_id, dt_atualizacao) values 
('Pizza', 12.50, 300, 5, 12, 1, now()),
('Queijo', 4.99, 400, 6, 10, 1, now()),
('Nuggets', 16.80, 350, 10, 6, 1, now());

insert into venda(dt_venda, is_pagto_prazo, nr_parcelas, usuario_id) values
('2021-02-25 10:58:51'::timestamp, false, 0, 2),
('2021-02-25 11:02:12'::timestamp, false, 0, 3),
('2021-02-25 15:02:32'::timestamp, true, 3, 2);
