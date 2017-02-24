package oracle.mau.http.common;

public interface StatusCode {
	public interface Common
	{
		public int FAIL=201;
		public int SUCCESS=200;

	}
	public interface Login
	{
		public int LOGIN_SUCCESS = 10001;
		public int LOGIN_FAIL = 10002;
		public int LOGIN_LIMIT = 10003;
	}
	public interface Dao
	{
		public int INSERT_SUCCESS = 20001;
		public int INSERT_FAIL = 20002;
		public int UPDATE_SUCCESS = 20003;
		public int UPDATE_FAIL = 20004;
		public int DELETE_SUCCESS = 20005;
		public int DELETE_FAIL = 20006;
		public int SELECT_SUCCESS = 20007;
		public int SELECT_FAIL = 20008;
	}
}