


create or replace function f_get_brand_connect_by_user(in i_user_id int)
returns table (
		id int,
		user_id int,
		brand_name varchar,
		auth_type varchar,
		account_identifier varchar,
		display_name varchar,
		access_token text,
		refresh_token text,
		expired_date timestamp,
		api_key varchar,
		api_secret varchar,
		additional_config text,
		actived varchar,
		synced_enable varchar,
		last_synced_at timestamp
		) as
$$
		begin
			return QUERY select bc.id, bc.user_id, b.brand_name, bc.auth_type, bc.account_identifier, bc.display_name, bc.access_token, bc.refresh_token,
							   bc.expired_date, bc.api_key, bc.api_secret, bc.additional_config, bc.actived, bc.synced_enable, bc.last_synced_at
						from "smarthub".t_brand_connection bc, "smarthub".t_brand b
						where bc.brand_id = b.brand_id
						  and bc.user_id = i_user_id;

		end;
$$ language plpgsql;

select * from "smarthub".t_brand_connection tbc;
select * from t_brand tb ;
select * from "smarthub".f_get_brand_connect_by_user(1);