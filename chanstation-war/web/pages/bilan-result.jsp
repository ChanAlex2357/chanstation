<%@page import="finance.*"%>
<%
    EtatDeFinance finance = (EtatDeFinance) session.getAttribute("etatFinancier");

%>
<div>
<h1>Résultat</h1>
<p>Bénéfices:<%= finance.getBenefice().getBenefMontant() %> </p>
<p>Dettes:<%= -finance.getDettes().sommerDebits(finance.getDettes().getSousEcrituresDetails()) + finance.getDettes().sommerCredit(finance.getDettes().getSousEcrituresDetails()) %> </p>
<p>Créances:<%= -finance.getCreances().sommerCredit(finance.getCreances().getSousEcrituresDetails())+finance.getCreances().sommerDebits(finance.getCreances().getSousEcrituresDetails())+1000000 %> </p>
</div>
