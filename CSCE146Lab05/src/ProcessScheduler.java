/*
 * Written by Michelle Clark
 */
//Personal note: functional code
public class ProcessScheduler 
{
    private LLQueue<Process> processes;
    private Process currentProcess;

    public ProcessScheduler()
    {
        this.currentProcess=null;
        //have to est. its a new LLQueue<Process>() to avoid null pointer exception when adding to
        this.processes=new LLQueue<Process>();
    }
    
    public Process getCurrentProcess()
    {
        //avoids null pointer exception
        if(this.currentProcess==null)
            return null;
        Process ret = this.currentProcess;
        return ret;
    }
    public void addProcess(Process in)
    {
        //if currentProcess is empty set it to input process
        if(this.currentProcess==null)
            this.currentProcess = in;
        //otherwise enqueue the inputted process
        else    
            this.processes.enqueue(in);
    }
    public void runNextProcess()
    {
        //setting the next process to the dequeued process from linked list of processes
        Process set = processes.dequeue();
        this.currentProcess = set;
    }
    public void cancelCurrentProcess()
    {
        this.currentProcess = null;
        //CHECK IF THIS IS ACCEPTABLE, easier to just call to run next process because already have the code
        runNextProcess();
    }
    public void printProcessQueue()
    {
        this.processes.print();
    }
}
