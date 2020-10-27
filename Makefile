######################
#      Makefile      #
######################

FILE_NAME = documentation.tex

LATEX = xelatex
BIBER = biber
RUSTY_SWAGGER = rusty-swagger

all: clean all1
all1: clean updateproject updatedoc swagger la la2 la3 
no: clean updateproject updatedoc swagger la la2 
docker-build: updateproject docker
doc: updatedoc swagger la la2 la3

updateproject:
	mvn -f dpppt-config-backend/pom.xml install

updatedoc:
	mvn -f dpppt-config-backend/pom.xml install -Dmaven.test.skip=true
	mvn springboot-swagger-3:springboot-swagger-3 -f dpppt-config-backend/pom.xml
	mkdir -p documentation/yaml
	cp dpppt-config-backend/generated/swagger/swagger.yaml documentation/yaml/sdk.yaml

swagger:
	cd documentation; $(RUSTY_SWAGGER) --file ../dpppt-config-backend/dpppt-config-backend/generated/swagger/swagger.yaml

la:
	cd documentation;$(LATEX) $(FILE_NAME)
bib:
	cd documentation;$(BIBER) $(FILE_NAME)
la2:
	cd documentation;$(LATEX) $(FILE_NAME)
la3:
	cd documentation;$(LATEX) $(FILE_NAME)
show:
	cd documentation; open $(FILE_NAME).pdf &

clean:
	@rm -f documentation/*.log documentation/*.aux documentation/*.dvi documentation/*.ps documentation/*.blg documentation/*.bbl documentation/*.out documentation/*.bcf documentation/*.run.xml documentation/*.fdb_latexmk documentation/*.fls documentation/*.toc
