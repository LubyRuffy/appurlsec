require 'pp'

def apk2java(apkpath, destdir=nil)
  destdir
end

def grephttp(destdir)
  #http:// and https://
end

def hosts_of_urls(urls)
  
end

def domains_of_hosts(hosts)
end

def ips_of_hosts(hosts)
end

def main()
  destdir = apk2java()
  urls = grephttp(destdir)
  hosts = hosts_of_urls(urls)
  domains = domains_of_hosts(hosts)
  ips = ips_of_hosts(hosts)
end
