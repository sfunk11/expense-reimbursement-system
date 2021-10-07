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






