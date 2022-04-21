--
-- Se deja a continuacion, las distintas consultas SQL realizadas para resolver los items del
-- punto 2 del challenge.
--
-- Ejercicio i.
-- Obtener cantidad de empleados dentro de los siguientes segmentos de sueldo :
-- SEGMENTO A: menor a USD 3500
-- SEGMENTO B: mayor o igual a USD 3500 y menor que UDS 8000
-- SEGMENTO C: mayor o igual a USD 8000
--
USE `myhotel-test`;
SELECT
	(
	CASE WHEN empl.SALARY < 3500 THEN 'SEGMENTO A'
	WHEN empl.SALARY BETWEEN 3500 AND 7999 THEN 'SEGMENTO B'
	ELSE 'SEGMENTO C' END) AS SEGMENTO, count(*) AS CANTIDAD_EMPLEADOS
FROM employees empl
WHERE empl.EMPLOYEE_ID NOT IN ( SELECT jh.EMPLOYEE_ID  FROM job_history jh) -- remove fired employees
GROUP BY (
	CASE WHEN empl.SALARY < 3500 THEN 'SEGMENTO A'
	WHEN empl.SALARY BETWEEN 3500 AND 7999 THEN 'SEGMENTO B'
	ELSE 'SEGMENTO C' END
);

-- OTRA ALTERNATIVA --
SELECT
SEGMENTO
,COUNT(*) AS EMPLOYEE_QUANTITY
FROM
(
	SELECT
		CASE
			WHEN empl.SALARY < 3500 THEN 'SEGMENTO A'
			WHEN empl.SALARY BETWEEN 3500 AND 7999 THEN 'SEGMENTO B'
			ELSE 'SEGMENTO C'
		END AS SEGMENTO
		, empl.EMPLOYEE_ID
	FROM employees empl
) TMP
GROUP BY (TMP.SEGMENTO)
ORDER BY TMP.SEGMENTO

--
-- Ejercicio ii.
-- Utilizando la tabla “departments” se requiere realizar la misma agrupación
-- de segmentos de sueldo, pero por departamento.
--
SELECT
SEGMENTO
,COUNT(*) AS CANTIDAD_DEPARTAMENTOS
FROM
(
	SELECT
		CASE
			WHEN empl.SALARY < 3500 THEN 'SEGMENTO A'
			WHEN empl.SALARY BETWEEN 3500 AND 7999 THEN 'SEGMENTO B'
			ELSE 'SEGMENTO C'
		END AS SEGMENTO
		,empl.DEPARTMENT_ID
	FROM employees empl
	INNER JOIN departments d on empl.DEPARTMENT_ID = d.DEPARTMENT_ID
) TMP
GROUP BY (TMP.SEGMENTO)
ORDER BY TMP.SEGMENTO

--
-- Ejercicio iii
-- Información del empleado con mayor sueldo de cada departamento.
--
SELECT  empl.*
FROM employees empl
INNER JOIN (
	SELECT max(e.SALARY) AS MAX_SALARY, DEPARTMENT_ID
	FROM employees e
	WHERE e.EMPLOYEE_ID NOT IN ( SELECT jh.EMPLOYEE_ID  FROM job_history jh) -- remove fired employees
	GROUP BY e.DEPARTMENT_ID
) dep_max_salary ON dep_max_salary.MAX_SALARY = empl.SALARY AND dep_max_salary.DEPARTMENT_ID = empl.DEPARTMENT_ID
ORDER BY DEPARTMENT_ID

--
-- Ejercicio iv.
-- Información de los gerentes que hayan sido contratados hace más de 15 años.
--
SELECT manager.*
FROM employees manager
INNER JOIN departments dep on dep.MANAGER_ID  = manager.EMPLOYEE_ID
WHERE TIMESTAMPDIFF(YEAR,manager.HIRE_DATE,CURRENT_DATE) > 15

--
-- Ejercicio v.
-- Salario promedio de todos los departamentos que tengan más de 10 empleados.
--
SELECT
dep.DEPARTMENT_ID
,dep.DEPARTMENT_NAME
,ROUND(AVG(empl.SALARY),2) AS AVERAGE_SALARY
FROM employees empl
INNER JOIN departments dep on empl.DEPARTMENT_ID  = dep.DEPARTMENT_ID
WHERE empl.DEPARTMENT_ID IN (
	SELECT e.DEPARTMENT_ID
	FROM employees e
	WHERE e.EMPLOYEE_ID NOT IN ( SELECT jh.EMPLOYEE_ID  FROM job_history jh)
	GROUP BY e.DEPARTMENT_ID
	HAVING COUNT(*) > 10
) GROUP BY empl.DEPARTMENT_ID

--
-- Ejercicio vi.
-- Obtener la siguiente información agrupada por país: cantidad empleados,
-- salario promedio, salario más alto, salario más bajo, promedio años antigüedad
--
SELECT
countries.COUNTRY_NAME
, COUNT(*) AS EMPLOYEES_NUMBER
, ROUND(AVG(empl.SALARY),2) AS AVERAGE_SALARY
, MAX(empl.SALARY) AS MAX_SALARY
, MIN(empl.SALARY) AS MIN_SALARY
, AVG(TIMESTAMPDIFF(YEAR,empl.HIRE_DATE,CURRENT_DATE)) AS ANTIQUITY_AVERAGE
FROM employees empl
INNER JOIN departments dep ON dep.DEPARTMENT_ID = empl.DEPARTMENT_ID
INNER JOIN locations loc ON loc.LOCATION_ID  = dep.LOCATION_ID
INNER JOIN countries countries ON countries.COUNTRY_ID = loc.COUNTRY_ID
WHERE empl.EMPLOYEE_ID NOT IN ( SELECT jh.EMPLOYEE_ID  FROM job_history jh) -- remove fired employees
GROUP BY(countries.COUNTRY_ID);
