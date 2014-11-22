需要实现以下这些方法，在代码里也有标明

private int df(String term);
private int tf(String term, Integer docID);
private int collectionScale(); //文章总数量，也可以是某个类别的文章的总数，如果是后者，需要传入docID计算
private float Ld(Integer docID); // 这篇文章的总长度（term数）

实现完这些方法以后，就可以用方法

public Integer[] BM25Ranking(Integer[] docIDLst, String query);

来排序，由于没有测试集，这个方法没有测试过，只是自己检查了几遍，发现问题可以告诉我

