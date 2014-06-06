#!/usr/bin/env ruby
require 'pp'
require 'pathname'

$basedir = Pathname.new(File.dirname(__FILE__)).realpath

def apk2java(apkpath, destdir=nil)
  jadx_cmd = "#{$basedir}/jadx/bin/jadx "
  jadx_cmd += "-d #{destdir} " if destdir
  jadx_cmd += apkpath
  puts jadx_cmd
  info = `#{jadx_cmd}`
  destdir ||= File.basename(apkpath).split('.')[0]
  destdir
end

def grephttp(destdir)
  #http:// and https://
  grep_cmd = "grep -irhEo \"http[s]?://[^'\\\"]*\" #{destdir}"
  puts grep_cmd
  info = `#{grep_cmd}`
  info.split(/[\r\n]/)
end

def hosts_of_urls(urls)
  
end

def domains_of_hosts(hosts)
end

def ips_of_hosts(hosts)
end

def main(argv)
  destdir = apk2java(argv[0])
  urls = grephttp(destdir)
  puts urls
  hosts = hosts_of_urls(urls)
  domains = domains_of_hosts(hosts)
  ips = ips_of_hosts(hosts)
end

main(ARGV)
