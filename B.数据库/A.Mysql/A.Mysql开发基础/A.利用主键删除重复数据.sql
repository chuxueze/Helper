删除数据，对于相同数据保留ID最大的

DELETE a
FROM
	USER a
JOIN (
	SELECT
		user_name,
		count(*),
		max(id) AS id
	FROM
		USER
	GROUP BY
		user_name
	HAVING
		count(*) > 1
) b ON a.user_name = b.user_name
a,tb_detail b where a.mLink=b.mLink
	a.id < b.id
