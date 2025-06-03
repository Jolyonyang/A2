package unisa.dse.a2.students;

public class UntradedCompanyException
{
	public UntradedCompanyException(String companyCode)
	{
		super(companyCode + " is not a listed company on this exchange");
	}
}
