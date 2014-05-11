<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns="http://www.w3.org/1999/xhtml">

    <xsl:output method="xml"
                doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
                encoding="UTF-8"
                indent="yes" />

    <xsl:template match="company">
        <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
                <title>Company <xsl:value-of select="name" /></title>
                <meta http-equiv="Content-Style-Type" content="text/css" />
                <link rel="stylesheet" href="company.css" type="text/css" media="screen"/>
            </head>

            <body>
                <h1>Company <xsl:value-of select="name" /></h1>
                <h2>Quick links</h2>
                <div class="box">
                    <strong>Quick links to divisions</strong>
                    <ul>
                        <xsl:apply-templates select="division" mode="links" />
                    </ul>
                </div>

                <div class="box">
                    <strong>Quick links to products</strong>
                    <ul>
                        <xsl:apply-templates select="//product" mode="links" />
                    </ul>
                </div>

                <h2>Divisions</h2>
                <xsl:apply-templates select="division" mode="list" />

                <h2>Products</h2>
                <xsl:apply-templates select="product" mode="list" />
            </body>
        </html>
    </xsl:template>

    <xsl:template match="division" mode="list">
        <div class="division">

            <div class="division-head">
                <span>Division:</span>
                <strong>
                    <a name="{@did}">
                        <xsl:value-of select="name" />
                    </a>
                    <xsl:value-of select="concat(' (', count(employees/employee), ' employees)')" />
                </strong>
            </div>

            <xsl:apply-templates select="head" />
            <xsl:apply-templates select="employees" />

            <p>
                <em>Total salaries: <xsl:value-of select="sum(employees//salary)" /> CZK</em>
            </p>

            <div>Products:</div>
            <ul>
                <xsl:variable name="did" select="@did" />
                <xsl:apply-templates select="//product[@produced-at=$did]" mode="links" />
            </ul>

        </div>
    </xsl:template>

    <xsl:template match="product" mode="list">
        <xsl:variable name="produced_at" select="@produced-at"/>
        <div class="product">
            <p>
                Product:
                <strong>
                    <a name="{@prod-id}">
                        <xsl:value-of select="name" />
                    </a>
                </strong>
            </p>
            <p>
                Produced at:
                <a href="#{@produced-at}">
                    <xsl:value-of select="../division[@did=$produced_at]/name" />
                </a>
            </p>
        </div>
    </xsl:template>

    <xsl:template match="division" mode="links">
        <li>
            <a href="#{@did}">
                <xsl:value-of select="name" />
            </a>
        </li>
    </xsl:template>

    <xsl:template match="//product" mode="links">
        <li>
            <a href="#{@prod-id}">
                <xsl:value-of select="name" />
            </a>
        </li>
    </xsl:template>

    <xsl:template match="person">
        <xsl:value-of select="concat(name, ' ', surname)" />
        <xsl:value-of select="concat(' (PID=', @pid, ')')" />
    </xsl:template>

    <xsl:template match="head">
        <span>Head:</span>
        <strong>
            <xsl:apply-templates select="person" />
        </strong>
    </xsl:template>

    <xsl:template match="employees">
        <div>Employees:</div>
        <ol>
            <xsl:apply-templates select="employee" />
        </ol>
    </xsl:template>

    <xsl:template match="employee">
        <li>
            <xsl:apply-templates select="person" />
        </li>
    </xsl:template>

</xsl:stylesheet>
