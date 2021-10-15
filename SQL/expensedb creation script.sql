create table ers_reimbursement_status(
	reimb_status_id integer primary key generated always as identity,
	reimb_status varchar(10)
);

create table ers_reimbursement_type(
	reimb_type_id integer primary key generated always as identity,
	reimb_type varchar(10)
);

create table ers_user_roles(
	ers_user_role_id integer primary key generated always as identity,
	user_role varchar(10)
);

create table ers_users(
	ers_users_id integer primary key generated always as identity,
	ers_username varchar(50),
	ers_password varchar(50),
	user_first_name varchar(100),
	user_last_name varchar(100),
	user_email varchar(150),
	user_role_id integer,
	constraint user_roles_fk foreign key (user_role_id) references ers_user_roles(ers_user_role_id),
	constraint ers_users_unv1 unique (ers_username, user_email)
);

insert into ers_users(ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)
	values ('cfunk', 'password', 'Cameron', 'Funk', 'cfunk@email.com', '1'),
		   ('mthompson', 'password', 'Michael', 'Thomspon', 'mthompson@email.com', '1'),
		   ('sfunk', 'password', 'Sam', 'Funk', 'sfunk@email.com', '2');

create table ers_reimbursement (
	reimb_id integer primary key generated always as identity,
	reimb_amount numeric,
	reimb_submitted Timestamp,
	reimb_resolved Timestamp,
	reimb_description varchar(250),
	reimb_receipt bytea,
	reimb_author integer,
	reimb_resolver integer,
	reimb_status_id integer,
	reimb_type_id integer,
	constraint ers_users_fk_auth foreign key (reimb_author) references ers_users(ers_users_id),
	constraint ers_users_fk_reslvr foreign key (reimb_resolver) references ers_users(ers_users_id),
	constraint ers_reimbursement_status_fk foreign key (reimb_status_id) references ers_reimbursement_status(reimb_status_id),
	constraint ers_reimbursement_type_fk foreign key (reimb_type_id) references ers_reimbursement_type(reimb_type_id)
);


CREATE OR replace FUNCTION reject_reimb(f_reimb_id int, f_resolver int)
RETURNS varchar(10) AS $$
begin 
	update ers_reimbursement set reimb_resolver=f_resolver, reimb_resolved= current_timestamp, reimb_status_id=reimb_id
			where reimb_id = f_reimb_id;
	return 'success';
end
$$ language 'plpgsql'

select * from ers_reimbursement r inner join ers_users u on r.reimb_author=u.ers_users_id where u.ers_username = 'cfunk';



select * from ers_users ;
select * from ers_reimbursement;

