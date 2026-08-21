-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090652161930190849, '智能体', 2090631284417122305, 1, 'agent', 'ai/agent/index', 'N', 'Y', 'C', '0', '0', 'ai:agent:list', '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '智能体菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090652161930190850, '智能体查询', 2090652161930190849, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:agent:query',        '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090652161930190851, '智能体新增', 2090652161930190849, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:agent:add',          '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090652161930190852, '智能体修改', 2090652161930190849, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:agent:edit',         '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090652161930190853, '智能体删除', 2090652161930190849, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:agent:remove',       '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090652161930190854, '智能体导出', 2090652161930190849, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:agent:export',       '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');
