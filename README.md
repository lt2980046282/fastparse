# fastparse
# 快速将json对象转为dto及entity依赖的类
# 代码生成器，可以根据json对应的数据类型转换成相应的java类
# 基础类包含service、impl、mapper、controller
# {
# 	"data": {		
# 		}
# 	},
# 	"funName": "index",
# 	"funType": "request",
# 	"funParams": 
# 		
# 			{"aa":"11","page":123}
# 		
# 	,
# 	"reqStyle": "restful"
# }
# data是要生成的实体类的对象
# funName是controller需要生成的方法名
# funType是mapping的请求类型
# reqStyle是根据用户所需要的生成相应的请求风格（包含resetful和普通风格）
