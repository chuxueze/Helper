1.步骤:








---------------------------------------------------------------------------------------------------------------------------------------------------
1.步骤:
1.1:
在实体类上对想着的字段实体添加注解
@Min(value = 18,message = "18以上才能添加")
private Integer age;

1.2:
在 Controller 层添加相应的注解，并判断抛出异常信息

 	@PostMapping(value = "/add")
    public void add(@Valid  @RequestBody Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return;
        }
    }