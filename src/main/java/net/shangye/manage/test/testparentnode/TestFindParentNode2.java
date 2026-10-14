package net.shangye.manage.test.testparentnode;

import java.util.*;

public class TestFindParentNode2 {

    public static void main(String[] args) {

        // 假设有一个包含 Cst 对象的列表
        List<Cst> cstList = new ArrayList<Cst>();

        // 添加一些示例数据
        cstList.add(new Cst("node1", "", "1"));
        cstList.add(new Cst("nodeA", "", "1"));
        cstList.add(new Cst("nodeC", "", "1"));
        cstList.add(new Cst("nodeB", "nodeC", "1"));
        cstList.add(new Cst("node2", "node1", "2"));
        cstList.add(new Cst("node3", "node2", "2"));
        cstList.add(new Cst("node4", "node3", "2"));
        cstList.add(new Cst("node5", "node1", "2"));
        cstList.add(new Cst("node6", "node5", "2"));
        cstList.add(new Cst("node7", "nodeA", "2"));

        Map<String, String> parentNode = findParentNode2(cstList);
        for (Map.Entry<String, String> entry : parentNode.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }


    }


    public static Map<String, String> findParentNode(List<Cst> cstList) {
        // <Id , ParentId（type=1）>
        Map<String, String> resultMap = new HashMap<>();
        Queue<Cst> queue = new LinkedList<>();
        for (Cst currentCst : cstList) {
            if(currentCst.getParentId() == null || "".equals(currentCst.getParentId())){
                resultMap.put(currentCst.getId(),currentCst.getParentId());
                continue;
            }
            queue.offer(currentCst);
            while (!queue.isEmpty()) {
                Cst node = queue.poll();
                Cst parentNode = null;
                for (Cst cst : cstList) if (cst.getId().equals(node.getParentId())) parentNode = cst;
                if (parentNode == null) resultMap.put(currentCst.getId(), node.getId());
                if (parentNode != null ) queue.offer(parentNode);
            }
        }
        return resultMap;
    }

    public static Map<String, String> findParentNode2(List<Cst> cstList) {
        // <Id , ParentId（type=1）>
        Map<String, String> resultMap = new HashMap<>();
        Map<String, Cst> cstMap = new HashMap<>();

        // 将所有节点存入映射，方便快速查找
        for (Cst cst : cstList) {
            cstMap.put(cst.getId(), cst);
        }

        for (Cst currentCst : cstList) {
            if (currentCst.getParentId() == null || currentCst.getParentId().isEmpty()) {
                resultMap.put(currentCst.getId(), currentCst.getParentId());
            } else {
                String parentId = currentCst.getParentId();
                // 找到最顶层的父节点
                while (cstMap.containsKey(parentId) && !cstMap.get(parentId).getParentId().isEmpty()) {
                    parentId = cstMap.get(parentId).getParentId();
                }
                resultMap.put(currentCst.getId(), parentId);
            }
        }
        return resultMap;
    }
}


