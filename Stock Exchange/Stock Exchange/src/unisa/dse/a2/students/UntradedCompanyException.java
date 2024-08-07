package unisa.dse.a2.students;

public class UntradedCompanyException extends Exception{

	private static final long serialVersionUID = 1L;

	public UntradedCompanyException(String companyCode){ // Raises a new exception if the company is not valid.
		System.out.println(companyCode + " is not a listed company on this exchange");
	}
}
