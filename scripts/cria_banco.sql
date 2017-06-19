create database db_pessoa;

use db_pessoa;

create table tbestado (
  codestado int(11) not null auto_increment,
  nome varchar(200) not null,
  sigla char(2) not null,
  status char(1) not null default 'a',
  primary key (codestado)
);

create table tbcidade (
  codcidade int(11) not null auto_increment,
  codestado int(11) not null,
  nome varchar(200) not null,
  status char(1) not null default 'a',
  primary key (codcidade),
  key fk_cidade_1_idx (codestado),
  constraint fk_cidade_1 foreign key (codestado) references tbestado (codestado) on delete no action on update no action
);

create table tbpessoafisica (
  codpessoafisica int(11) not null auto_increment,
  cpf varchar(14) default null,
  rg varchar(20) default null,
  datanascimento date default null,
  primary key (codpessoafisica),
  unique key cpf_unique (cpf)
);

create table tbpessoajuridica (
  codpessoajuridica int(11) not null auto_increment,
  cnpj varchar(18) default null,
  ie varchar(20) default null,
  razaosocial varchar(200) default null,
  primary key (codpessoajuridica),
  unique key cnpj_unique (cnpj)
);

create table tbpessoa (
  codpessoa int(11) not null auto_increment,
  codpessoafisica int(11) default null,
  codpessoajuridica int(11) default null,
  codcidade int(11) not null,
  nome varchar(200) not null,
  endereco varchar(200) default null,
  telefone varchar(20) default null,
  datacadastro date default null,
  email varchar(200) default null,
  status char(1) not null default 'a',
  primary key (codpessoa),
  key fk_tbpessoa_1_idx (codpessoafisica),
  key fk_tbpessoa_2_idx (codpessoajuridica),
  key fk_tbpessoa_3_idx (codcidade),
  constraint fk_tbpessoa_1 foreign key (codpessoafisica) references tbpessoafisica (codpessoafisica) on delete no action on update no action,
  constraint fk_tbpessoa_2 foreign key (codpessoajuridica) references tbpessoajuridica (codpessoajuridica) on delete no action on update no action,
  constraint fk_tbpessoa_3 foreign key (codcidade) references tbcidade (codcidade) on delete no action on update no action
);


create table tbcliente (
  codpessoa int(11) not null,
  primary key (codpessoa),
  constraint fk_cliente_1 foreign key (codpessoa) references tbpessoa (codpessoa) on delete no action on update no action
);


create table tbfornecedor (
  codpessoa int(11) not null,
  primary key (codpessoa),
  constraint fk_fornecedor_1 foreign key (codpessoa) references tbpessoa (codpessoa) on delete no action on update no action
);


create table tbfuncionario (
  codpessoa int(11) not null,
  dataadmissao date not null,
  datademissao date default null,
  primary key (codpessoa),
  constraint fk_funcionario_1 foreign key (codpessoa) references tbpessoa (codpessoa) on delete no action on update no action
);




