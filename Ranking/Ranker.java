/**
 * @description A class of Ranking with BM25 Algorithm
 * @author Carl 
 * @createtime 11/22/2014
 */


import java.util.*;

public class Ranker {
	
	/**
	 * @test 排序
	 * 
	public static void main(String[] args) throws Exception {
		Map<Integer, Float> dict = new Hashtable<Integer, Float>();
		dict.put(1, 0.0755f);
		dict.put(2, 0.325f);
		dict.put(3, 0.0745f);
		dict.put(4, 0.10325f);
		dict.put(5, 0.046f);
		System.out.println(sortByValue(dict).keySet());
	}
	*/

	/**********************
	 * 以下方法不需要修改
	 **********************/

	/**
	 * k1, 建议取值区间1.2~2.0
	 */
	private float k1() {
		return 1.5f;
	}

	/**
	 * b, 建议取值0.75
	 */
	private float b() {
		return 0.75f;
	}

	/**********************
	 * 请修改需要修改, 请根据lucene分好词后的词典进行实现
	 **********************/

	/**
	 * average of all documents
	 */
	private float Lave() {
		// 请修改
		return 150.0f;
	}

	/**
	 * document frequency
	 */
	private int df(String term) {
		// 需实现
		int df = 0;
		return df;
	}

	/**
	 * term frequency
	 */
	private int tf(String term, Integer docID) {
		// 需实现
		int tf = 0;
		return tf;
	}

	/**
	 * number of documents
	 */
	private int collectionScale() {
		// 请修改
		return 100;
	}

	/**
	 * length of doc
	 */
	private float Ld(Integer docID) {
		// 需实现
		return 1.f;
	}

	/**
	 * @param docIDLst
	 * 传进来的docIDLst表示一个无序的docID列表 这就需要实现上面关于tf和df的方法，才能 在这个方法里取得相应的值。
	 * 
	 * 当然，还有另一种形式可以表示doc列表，我 猜想lucene会有doc对象，如果doc对象有方便
	 * 的方法可以帮助查找一个term在这个对象里的tf， 那这里的参数可以改成docObj的列表，请根据 实际情况修改。
	 * 
	 * @param query
	 * 分好词以空格为间隔的query
	 */
	public Integer[] BM25Ranking(Integer[] docIDLst, String query) {

		String[] terms = query.split(" ");
		Map<Integer, Float> dict = new Hashtable<Integer, Float>();

		for (int i = 0; i < docIDLst.length; i++) {

			float RSVSum = 0.0f;
			Integer docID = docIDLst[i];

			for (int j = 0; j < terms.length; j++) {
				String term = terms[j];
				
				//如果df为0，表示这个term没有在任何一篇文章出现过，那么忽略他对文章的贡献度
				int df = this.df(term);
				if (df == 0)
					continue;
				
				//如果tf为0，表示这个term没有在这篇文章出现过，那么他对文章没有贡献度，不需计算
				int tf = this.tf(term, docID);
				if (tf == 0)
					continue;

				float idf = this.collectionScale() / (float) df;
				float b = this.b();
				float k1 = this.k1();
				float Lave = this.Lave();
				float Ld = this.Ld(docID);
				float x = idf * (k1 + 1) * tf
						/ (k1 * ((1 - b) + b * (Ld / Lave)) + tf); // BM25主公式
				RSVSum += Math.log(Math.max(x, 0.0f));
			}

			dict.put(docID, RSVSum);
		}
		// 将dict的keyset(排好序的ID)提取出来，返回
		Integer[] result = new Integer[dict.size()];
		return sortByValue(dict).keySet().toArray(result);
	}
	
	/**
	 * 将一个 key-value 容器按照value的值由大到小排序
	 */
	public static Map<Integer, Float> sortByValue(Map<Integer, Float> map) {

		List<Map.Entry<Integer, Float>> list = new LinkedList<Map.Entry<Integer, Float>>(
				map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Integer, Float>>() {
			public int compare(Map.Entry<Integer, Float> o1,
					Map.Entry<Integer, Float> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<Integer, Float> result = new LinkedHashMap<Integer, Float>();
		for (Map.Entry<Integer, Float> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}
}
