import java.util.Date;


public class Task implements Comparable<Task>
{
	private String _name;
	private String _note;
	private Date _dueDate;
	private Date _completeDate;
	private Date _reminderDate;
	private Date _createDate;
	private int _difficulty = -1;
	private int _importance = -1;
	private int _progress = -1;
	private int _maxProgress = -1;
	private TaskStatus _taskStatus;
	
	public String getName() throws Exception
	{
		if (_name == null)
		{
			throw new Exception("name is null");
		}
		return _name;
	}
	public void setName(String strname) throws Exception
	{
		if (isTaskEditable(true))
		{
			_name = strname;
		}
		else
		{
			throw new Exception("Task's status is not valid for setting new name");
		}
	}
	public String getNote() throws Exception
	{
		if (_note == null)
		{
			throw new Exception("note is null");
		}
		return _note;
	}
	public void setNote(String strnote) throws Exception
	{
		if (isTaskEditable(true))
		{
			_note = strnote;
		}
		else
		{
			throw new Exception("Task's status is not valid for setting new note");
		}
	}
	public Date getDueDate() throws Exception
	{
		if (_dueDate == null)
		{
			throw new Exception("due date is null");
		}
		return _dueDate;
	}
	public void setDueDate(Date newdate) throws Exception
	{
		if (isTaskEditable(true))
		{
			_dueDate = newdate;
		}
		else
		{
			throw new Exception("Task's status is not valid for setting new due date");
		}
	}
	public Date getCompleteDate() throws Exception
	{
		if (_completeDate == null)
		{
			throw new Exception("complete date is null");
		}
		return _completeDate;
	}
	public int getDifficulty() throws Exception
	{
		if (_difficulty == -1)
		{
			throw new Exception("difficulty is null (-1)");
		}
		return _difficulty;
	}
	public void setDifficulty(int diff) throws Exception
	{
		if (diff <= 0 || diff > 10)
		{
			throw new Exception("new diff is not in valid range 1 <= diff <= 10");
		}
			
		if (isTaskEditable(true))
		{
			_difficulty = diff;
		}
		else
		{
			throw new Exception("Task's status is not valid for setting new difficulty");
		}
	}
	public int getImportance() throws Exception
	{
		if (_importance == -1)
		{
			throw new Exception("importance is null (-1)");
		}
		return _importance;
	}
	public void setImportance(int newimport) throws Exception
	{
		if (newimport <= 0 || newimport > 10)
		{
			throw new Exception("new import is not in valid range 1 <= import <= 10");
		}
		if (isTaskEditable(true))
		{
			_importance = newimport;
		}
		else
		{
			throw new Exception("Task's status is not valid for setting new importance");
		}
	}
	public int getProgress() throws Exception
	{
		if (_progress == -1)
		{
			throw new Exception("progress is null (-1)");
		}
		return _progress;
	}
	public void setProgress(int newprog) throws Exception
	{
		if (newprog < 0 || newprog > _maxProgress)
		{
			throw new Exception("new prog is not in valid range 0 <= prog <= max progress");
		}
		if (isTaskEditable(false))
		{
			_progress = newprog;
		}
		else
		{
			throw new Exception("Task's status is not valid for setting new progress");
		}
		checkIfCompleted();
	}
	public int getMaxProgress() throws Exception
	{
		if (_maxProgress == -1)
		{
			throw new Exception("max progress is null (-1)");
		}
		return _maxProgress;
	}
	public void setMaxProgress(int newmax) throws Exception
	{
		if (newmax < 0)
		{
			throw new Exception("new max is not in valid range newmax >= 0");
		}
		if (isTaskEditable(false))
		{
			_maxProgress = newmax;
		}
		else
		{
			throw new Exception("Task's status is not valid for setting new maximium progress");
		}
		checkIfCompleted();
	}
	public Date getReminderDate() throws Exception
	{
		if (_reminderDate == null)
		{
			throw new Exception("reminder date is null");
		}
		return _reminderDate;
	}
	public void setReminderDate(Date date) throws Exception
	{
		if (isTaskEditable(true))
		{
			_reminderDate = date;
		}
		else
		{
			throw new Exception("the task is not valid for setting new reminder date");
		}
	}
	
	public void switchTaskOnhold() throws Exception
	{
		if (_taskStatus == TaskStatus.ABORT)
		{
			throw new Exception("Task is in a state of abort, cannot switch to onhold");
		}
		if (_taskStatus == TaskStatus.COMPLETED)
		{
			throw new Exception("Task is in a state of completed, cannot switch to onhold");
		}
		
		
		if ((_taskStatus == TaskStatus.NOT_STARTED) ||
				(_taskStatus == TaskStatus.IN_PROGRESS))
		{
			_taskStatus = TaskStatus.ON_HOLD;
		}
		else if (_taskStatus == TaskStatus.ON_HOLD)
		{
			if (_progress == 0)
			{
				_taskStatus = TaskStatus.NOT_STARTED;
			}
			else
			{
				_taskStatus = TaskStatus.IN_PROGRESS;
			}
		}
	}
	
	/***
	 * check if the task is completed, and perform relevant actions
	 * if so
	 * this is called after setting new progress and new maxProgress
	 */
	public void checkIfCompleted()
	{
		if (_progress >= _maxProgress)
		{
			// the task is completed
			_completeDate = new Date();
			_taskStatus = TaskStatus.COMPLETED;
		}
	}

	public void abortTask()
	{
		_taskStatus = TaskStatus.ABORT;
	}
	
	public int getPriorityIndex()
	{
		int pri = 0;
		
		int diff = 1;
		if (_difficulty != -1)
		{
			diff = _difficulty;
		}
		int imp = 1;
		if (_importance != -1)
		{
			imp = _importance;
		}
		
		if (_dueDate != null && _progress != -1 && _maxProgress > 0)
		{
			double dater = (new Date().getTime() - _createDate.getTime()) /
					(_dueDate.getTime() - _createDate.getTime());
			double progr = _progress / _maxProgress;
			if (dater >= progr)
			{
				// progress is falling behind
				pri += (dater - progr) * (11 - diff) * (imp);
			}
			
		}
		
		if (_dueDate != null)
		{
			double dater = (new Date().getTime() - _createDate.getTime()) /
					(_dueDate.getTime() - _createDate.getTime());
			pri += dater * 10;
		}
		
		return pri;
	}
	
	public Task(String n)
	{
		_name = n;
		_createDate = new Date();
	}
	
	/***
	 * this function check if the status of this task is either
	 * NOT_STARTED or IN_PROGRESS, if so, the fields can be mutated,
	 * otherwise the fields cannot be mutated, except _taskStatus
	 * @param canBeOnHold determines if ON_HOLD is true, can fields change
	 * @return if the task is mutable
	 */
	private boolean isTaskEditable(boolean canBeOnHold)
	{
		return ((_taskStatus == TaskStatus.NOT_STARTED) ||
				(_taskStatus == TaskStatus.IN_PROGRESS)) &&
				((canBeOnHold)?true:!(_taskStatus==TaskStatus.ON_HOLD));
	}
	
	
	@Override
	public int compareTo(Task t)
	{
		return this.getPriorityIndex() - t.getPriorityIndex();
	}

	
}
