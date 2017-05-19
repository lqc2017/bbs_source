package grp3022.bbs.type;

public enum Tag {
	/*language*/
	C(10000,"c"), CPLUS(10025,"c++"), JAVA(10050,"java"), JAVASCRIPT(10100,"javascript"), PHP(10150,"php"), 
	PERL(10200,"perl"), PAYTHON(10250,"paython"), RUBY(10300,"ruby"), OBJECTIVE_C(10350,"objective-c"), 
	GO(10400,"go"), NODEJS(10450,"node.js"), ERLANG(10500,"erlang"), SCALA(10550,"scala"), BASH(10600,"bash"), 
	ACTIONSCRIPT(10650,"actionscript"),
	
	/*system*/
	IOS(15000,"ios"), ANDROID(15050,"android"), WINDOWS(15100,"windows"), LINUX(15150,"linux"), UNIX(15200,"unix"),
	
	/*database*/
	MYSQL(20000,"mysql"), SQLITE(20050,"sqlite"), ORACLE(20100,"oracle"), NOSQL(20150,"nosql"), MONGODB(20200,"mongodb"), 
	REDIS(20250,"redis"),
	
	/*SERVER*/
	APACHE(25000,"apache"), NGINX(25050,"nginx"),
	
	/*FRONT-END*/
	HTML(30000,"html"), HTML5(30050,"html5"), CSS(30100,"css"), CSS3(30150,"css3"), JQUERY(30200,"jquery"), JSON(30250,"json"), 
	AJAX(30300,"ajax"), BOOTSTRAP(30350,"bootstrap");
	
    private Integer index;
    private String name;
    
    private Tag(Integer index,String name) {  
        this.index = index; 
        this.name = name;
    }  
    public Integer getIndex() {  
        return index;  
    }  
    
    public void setIndex(Integer index) {  
        this.index = index;  
    }
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
    
}
