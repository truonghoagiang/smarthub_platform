

create or replace function f_is_exists_by_brand_and_device(i_brand_id in int, i_device_id int)
returns boolean as
$$
declare
o_result boolean;
m_cnt int;
	begin
		select count(*) into o_result
		 from "smarthub".t_user_devices td
		where td.device_type = i_device_id
		  and td.brand_id = i_brand_id;

	if found then
		return o_result;
	else
		return false;
	end if;

	end;
$$ language plpgsql;

--select * from t_user_devices;