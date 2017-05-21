package grp3022.bbs.type;

public enum Tag {
	/*language*/
	C(10000,"c","#996666"), CPLUS(10025,"c++","#998066"), JAVA(10050,"java","#999966"), 
	JAVASCRIPT(10100,"javascript","#809966"), PHP(10150,"php","#996680"), PERL(10200,"perl","#B18B8B"), 
	PAYTHON(10250,"paython","#CAAFAF"), RUBY(10300,"ruby","#669966"), OBJECTIVE_C(10350,"objective-c","#996699"), 
	GO(10400,"go","#AFCACA"), NODEJS(10450,"node.js","#8BB1B1"), ERLANG(10500,"erlang","#669980"), 
	SCALA(10550,"scala","#806699"), BASH(10600,"bash","#666699"),ACTIONSCRIPT(10650,"actionscript","#668099"),
	
	/*system*/
	IOS(15000,"ios","#669999"), ANDROID(15050,"android","#A37575"), WINDOWS(15100,"windows","#A38C75"), 
	LINUX(15150,"linux","#A3A375"), UNIX(15200,"unix","#8CA375"),
	
	/*database*/
	MYSQL(20000,"mysql","#A3758C"), SQLITE(20050,"sqlite","#BC9A9A"), ORACLE(20100,"oracle","#D4BFBF"), 
	NOSQL(20150,"nosql","#75A375"), MONGODB(20200,"mongodb","#A375A3"), 
	REDIS(20250,"redis","#BFD4D4"),
	
	/*SERVER*/
	APACHE(25000,"apache","#9ABCBC"), NGINX(25050,"nginx","#75A38C"),
	
	/*FRONT-END*/
	HTML(30000,"html","#8C75A3"), HTML5(30050,"html5","#7575A3"), CSS(30100,"css","#758CA3"), 
	CSS3(30150,"css3","#75A3A3"), JQUERY(30200,"jquery","#AD8585"), JSON(30250,"json","#AD9985"), 
	AJAX(30300,"ajax","#ADAD85"), BOOTSTRAP(30350,"bootstrap","#99AD85");
	
    private Integer index;
    private String name;
    private String color;
    
    private Tag(Integer index,String name,String color) {  
        this.index = index; 
        this.name = name;
        this.color = color;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
    
}
