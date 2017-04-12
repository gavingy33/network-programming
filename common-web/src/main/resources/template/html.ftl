<html>
	<body>
		Hello ${map.name} !
		Hello ${amount?string("#.00")} !
		
		<ul>
		<#list list as each>
			<li>${each?html}</li>
		<#else><#-- 列表中没有元素 -->
			NONE
		</#list>
		<ul>
		
		<#include "include.html">
	</body>
</html>