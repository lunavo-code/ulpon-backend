create table geo_keyword
(
    id          bigint primary key auto_increment not null comment 'id',
    keyword     varchar(64)                       not null comment '关键词（主关键词，只能一个，不低于3个字）',
    hit         varchar(1024)                     not null comment '达标命中（达标命中信息，逗号隔开，比如：xx科技公司，xx科技）',
    status      char(1)    default '0' comment '状态（0正常 1停用）',
    del_flag    char(1)    default '0' comment '删除标志（0代表存在 1代表删除）',
    create_dept bigint(20) default null comment '创建部门',
    create_by   bigint(20) default null comment '创建者',
    create_time datetime comment '创建时间',
    update_by   bigint(20) default null comment '更新者',
    update_time datetime comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='GEO核心词库表';

create table geo_title
(
    id          bigint primary key auto_increment not null comment 'id',
    keyword_id  bigint                            not null comment '关键词id',
    title       varchar(256)                      not null comment '标题',
    included    char(1)    default '0' comment '收录状态',
    status      char(1)    default '0' comment '状态（0正常 1停用）',
    del_flag    char(1)    default '0' comment '删除标志（0代表存在 1代表删除）',
    create_dept bigint(20) default null comment '创建部门',
    create_by   bigint(20) default null comment '创建者',
    create_time datetime comment '创建时间',
    update_by   bigint(20) default null comment '更新者',
    update_time datetime comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='GEO标题生成';

create table geo_writing_Instruction
(
    id          bigint primary key auto_increment not null comment 'id',
    name varchar(128) not null comment '指令名称',


) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='GEO写作指令';


create table geo_article_class
(
    id          bigint primary key auto_increment not null comment 'id',
    article     varchar(256)                      not null comment '分类',
    status      char(1)    default '0' comment '状态（0正常 1停用）',
    del_flag    char(1)    default '0' comment '删除标志（0代表存在 1代表删除）',
    create_dept bigint(20) default null comment '创建部门',
    create_by   bigint(20) default null comment '创建者',
    create_time datetime comment '创建时间',
    update_by   bigint(20) default null comment '更新者',
    update_time datetime comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='GEO文章分类';

create table geo_article
(
    id          bigint primary key auto_increment not null comment 'id',
    article_id  bigint                            not null comment '分类ID',
    title       varchar(256)                      not null comment '文章标题',
    included    char(1)    default '0' comment '收录状态',
    status      char(1)    default '0' comment '状态（0正常 1停用）',
    del_flag    char(1)    default '0' comment '删除标志（0代表存在 1代表删除）',
    create_dept bigint(20) default null comment '创建部门',
    create_by   bigint(20) default null comment '创建者',
    create_time datetime comment '创建时间',
    update_by   bigint(20) default null comment '更新者',
    update_time datetime comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='GEO文章';
