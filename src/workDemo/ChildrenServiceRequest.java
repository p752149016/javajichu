package workDemo;

import util.SendRequestUtil;

public class ChildrenServiceRequest {

	private static String PROTOCOL = "http://";
//	private static String DOMAIN_NAME = "localhost";
	private static String DOMAIN_NAME = "www.longwel.com.cn";
	private static String PORT = ":80";
	private static String PROJECT_NAME = "/Longwel";
	
	public static String CHILDREN_MUSIC = "/childrenMusic/search.do";							//音乐
	public static String CHILDREN_NATURE = "/childrenNature/search.do";							//自然
	public static String CHILDREN_PAINTING = "/childrenPaintingAppreciation/search.do";			//绘画欣赏
	public static String CHILDREN_POETRY = "/childrenPoetry/search.do";							//古诗歌
	public static String CHILDREN_SONG = "/childrenSong/search.do";								//儿歌
	public static String CHILDREN_STORY = "/childrenStory/search.do";							//故事
	
	private static String parameter(Integer pageNum, Integer pageSize, String word){
		return "?pageNum=" + pageNum + "&pageSize=" + pageSize + "&word=" + word;
	}
	
	/**
	 * @param path      路径
	 * @param pageNum	页码
	 * @param pageSize	每页的数量
	 * @param word		搜索的词（赋值为""时，普通查询,不能为null）
	 * @return
	 */
	public static String getData(String path, Integer pageNum, Integer pageSize, String word){
		String url = PROTOCOL + DOMAIN_NAME + PORT + PROJECT_NAME + path + parameter(pageNum, pageSize, word);
		return SendRequestUtil.sendGet(url);
	}
	
	public static void main(String[] args){
		System.out.println(getData(CHILDREN_SONG, 1, 10, ""));
	}
}
