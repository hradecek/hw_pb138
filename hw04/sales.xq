<sales>
{
    for $product in doc("company.xml")//product
    return
        <product>
        {
            $product/name
        }
        <produced-in>
        {
            data(doc("company.xml")//division[@did=$product/@produced-at]/name)
        }
        </produced-in>
        {
            for $currency in ('CZK', 'EUR')
            let $sold := doc("company.xml")//sold[@prod-id=$product/@prod-id]
            where $sold/@currency=$currency
            return 
                <sold currency="{$currency}" total="{sum($sold[@currency=$currency]/@price)}" />
        }
        </product>
}
</sales>
