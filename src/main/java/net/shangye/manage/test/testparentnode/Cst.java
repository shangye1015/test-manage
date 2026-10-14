package net.shangye.manage.test.testparentnode;

public  class Cst {

    private String id;

    private String parentId;

    private String type;

    public Cst() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Cst(String node, String parentNode, String type) {
        this.id = node;
        this.parentId = parentNode;
        this.type = type;
    }
}
