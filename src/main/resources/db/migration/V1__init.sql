create table grades
(
    id         bigint auto_increment
        primary key,
    grade      int    null,
    lesson_id  bigint null,
    student_id bigint null,
    constraint FK13a16545m7vvrcspc999r15s9
        foreign key (student_id) references students (id),
    constraint FKmt7ytccr6ebljxbu05mlb5fif
        foreign key (lesson_id) references lessons (id)
);

create table lessons
(
    id          bigint auto_increment
        primary key,
    date        datetime(6)  null,
    is_complete bit          null,
    title       varchar(255) null,
    group_id    bigint       null,
    teacher_id  bigint       null,
    topic_id    bigint       null,
    constraint FKbr76cuebuufbbltujpfq04tto
        foreign key (teacher_id) references teachers (id),
    constraint FKn1lqfilldva7f2y31fxf3p4p1
        foreign key (topic_id) references topics (id)
);

create table students
(
    id         bigint auto_increment
        primary key,
    email      varchar(255) not null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    password   varchar(255) not null,
    group_id   bigint       null,
    teacher_id bigint       null,
    constraint UK_4hkb9j30e6qun3qyswqy0x53c
        unique (email),
    constraint UK_e2rndfrsx22acpq2ty1caeuyw
        unique (email),
    constraint FKbrb7umgbkqrmj9lfwkf0p2r7r
        foreign key (teacher_id) references teachers (id)
);

create table teachers
(
    id         bigint auto_increment
        primary key,
    email      varchar(255) not null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    password   varchar(255) not null,
    constraint UK_4l9jjfvsct1dd5aufnurxcvbs
        unique (email),
    constraint UK_i8ha3ykankg90d34qe21p5wl3
        unique (email)
);

create table topics
(
    id         bigint auto_increment
        primary key,
    name       varchar(255) null,
    teacher_id bigint       null,
    constraint FKpdumnpgln2ugp7no0o3xran28
        foreign key (teacher_id) references teachers (id)
);

create table authorities
(
    id    int primary key,
    value text not null
);

create unique index authorities_value_uindex on authorities (value);

create table user_authorities
(
    user_id      bigint not null,
    authority_id int    not null,
    primary key (user_id, authority_id),
    constraint user_authorities_users_fk foreign key (user_id)
        references users (id) on delete cascade,
    constraint user_authorities_authorities_fk foreign key (authority_id)
        references authorities (id) on delete cascade
);

insert into authorities (id, value)
values (0, 'ROLE_TEACHER');
insert into authorities (id, value)
values (1, 'ROLE_STUDENT');

create table refresh_tokens
(
    value     uuid        not null primary key,
    user_id   bigint      not null,
    issued_at timestamptz not null,
    expire_at timestamptz not null,
    next      uuid,
    constraint refresh_tokens_user_fk foreign key (user_id)
        references users (id) on delete cascade,
    constraint refresh_tokens_next_fk foreign key (next)
        references refresh_tokens (value) on delete cascade
);

create procedure prune_refresh_tokens()
    language SQL
as
$$
delete
from refresh_tokens rt
where rt.expire_at < current_timestamp
   or rt.user_id in (select u.id from users u where u.id = rt.user_id and u.status = 1)
$$;
