/*
 * Written by Michelle Clark
 */
public class OutOfPriceRangeException extends Exception
{
    //decided not to torture the user with exceptions today
    public OutOfPriceRangeException()
    {
        super("Tried to guess a price out of range");
    }
    public OutOfPriceRangeException(String msg)
    {
        super(msg);
    }
}
