

create or replace function f_is_exists_by_user_and_brand(in i_user_id int, in i_brand_id int)
returns boolean as
$$
declare
o_result boolean;
m_cnt int;
	begin
		select count(*) into o_result
		 from "smarthub".t_brand_connection bc
		where bc.user_id = i_user_id
		  and bc.brand_id = i_brand_id;

	if found then
		return o_result;
	else
		return false;
	end if;

	end;
$$ language plpgsql;

