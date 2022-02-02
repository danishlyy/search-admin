create database search;
create user search_admin with password 'search_admin';
grant all privileges on database search to search_admin;