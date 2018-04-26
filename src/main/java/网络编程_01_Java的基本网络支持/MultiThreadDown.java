package 网络编程_01_Java的基本网络支持;
/**
 * 
 * @author Eric_Junior
 * 创建时间：2017年9月21日
 */
public class MultiThreadDown {
	public static void main(String[] args) throws Exception {
		// 初始化DownUtil对象
		// 下载QQ
		final DownUtil downUtil = new DownUtil("http://w.x.baidu.com/alading/anquan_soft_down_all/12350","D:\\QQ.exe", 4);
		// 开始下载
		downUtil.download();
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (downUtil.getCompleteRate() < 1) {
					// 每隔0.1秒查询一次任务的完成进度，
					// GUI程序中可根据该进度来绘制进度条
					System.out.println("已完成：" + downUtil.getCompleteRate());
					try {
						Thread.sleep(1000);
					} catch (Exception ex) {
					}
				}
				System.out.println("下载完成！");
			}
		}).start();
	}
}
