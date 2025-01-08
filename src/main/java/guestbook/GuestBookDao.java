package guestbook;

import java.util.List;

public interface GuestBookDao {
	public abstract List<GuestBookVo> getList();
	public abstract boolean insert(GuestBookVo vo);
	public abstract boolean delete(Integer no, String password);
}