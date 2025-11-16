


create or replace function f_get_devices_by_brand_and_device(i_brand_id in int, i_device_id in int)
returns table (
		id int,
		brand_name varchar,
		auth_type varchar,
		name varchar,
		device_type varchar,
		room varchar,
		is_online boolean,
		is_active boolean,
		capabilities jsonb,
		current_state jsonb,
		properties jsonb,
		model varchar,
		firmware_version varchar,
		last_actived timestamp
		) as
$$
		begin
			return QUERY select td.id, bb.brand_name, tb.name, tt.type_name, rr.room_name, td.is_online, td.is_active, td.capbilities, td.current_statem,
							td.properties, td.model, td.firmware_version, td.last_actived
						from "smarthub".t_user_devices td, "smarthub".t_device_type tt, "smarthub".t_room rr, "smarthub".t_brand bb
						where td.brand_id = i_brand_id and td.device_type = tt.type_id
						  and td.brand_id = bb.brand_id and td.room_id = rr.id
						  and td.device_type = i_device_id;
						  

		end;
$$ language plpgsql;

--select * from t_user_devices tud ;