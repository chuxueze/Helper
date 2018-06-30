select b.owner, b.object_name, a.session_id, a.locked_mode
  from v$locked_object a, dba_objects b
 where b.object_id = a.object_id;


alter system kill session '36,2079';