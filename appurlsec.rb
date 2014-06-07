#!/usr/bin/env ruby
require 'pathname'
require 'optparse'
require 'logger'
require 'netaddr'

$:.unshift(File.expand_path(File.dirname(__FILE__)))
.unshift(File.expand_path(File.join(File.dirname(__FILE__), 'lib')))

require 'lrurl'
include LubyRuffyUrl

class OptsConsole
  def self.parse(args)
    options = { :dir=>nil, :apk=>nil, :log_level=>1 }

    opts_ = OptionParser.new do |opts|
      opts.banner = "APP url security scanner By LubyRuffy"
      opts.separator "Usage : #{$0} [OPTIONS]"

      opts.separator ""
      opts.separator "Common options:"

      opts.on("-d", "--dir <dir>", "Process directory, not apk") do |t|
        options[:dir] = t
      end

      opts.on("-a", "--apk <apkpath>", "Process apk file") do |t|
        options[:apk] = t
      end

      opts.on("-l", "--level <level>", "Log level, DEFAULT=0 (0 error; 1 info; 2 debug)") do |p|
        options[:log_level] = p.to_i
      end

      opts.on_tail("-h", "--help", "Show this message") do
        puts opts
        exit
      end
    end

    begin
      opts_.parse!(args)
      if !options[:dir] and !options[:apk]
        puts "Error: at least -d or -a parameter."
        exit
      end
    rescue OptionParser::InvalidOption
      puts "Invalid option, try -h for usage"
      exit
    end

    options
  end
end

$basedir = Pathname.new(File.dirname(__FILE__)).realpath
@options = OptsConsole.parse(ARGV)
@logger ||= Logger.new(STDOUT)
@logger.level = Logger::INFO
@logger.level = case @options[:log_level]
                  when 0 then Logger::ERROR
                  when 1 then Logger::INFO
                  when 2 then Logger::DEBUG
                  else Logger::ERROR
                end

def apk2java(apkpath, destdir=nil)
  jadx_cmd = "#{$basedir}/jadx/bin/jadx "
  jadx_cmd += "-d #{destdir} " if destdir
  jadx_cmd += apkpath
  @logger.info jadx_cmd
  info = `#{jadx_cmd}`
  destdir ||= File.basename(apkpath, ".*")
  destdir
end

def grephttp(destdir)
  #http:// and https://
  grep_cmd = "grep -irhEo \"http[s]?://[^'\\\"]*\" #{destdir}"
  @logger.info grep_cmd
  info = `#{grep_cmd}`
  remove_bigbrother info.split(/[\r\n]/).compact.uniq.sort
end

def remove_bigbrother(hosts)
  hosts = hosts.select {|h|
    h && !h.include?('.mmarket.com') && !h.include?('.douban.com') && !h.include?('.189store.com') && !h.include?('xmlpull.org') && !h.include?('.xmlsoap.org') && !h.include?('.google.com') && !h.include?('.w3.org') && !h.include?('.renren.com') && !h.include?('.weibo.cn') && !h.include?('.weibo.com') && !h.include?('.gtimg.cn') && !h.include?('.qplus.com') && !h.include?('.qcloud.com') && !h.include?('.adview.cn') && !h.include?('.adwo.com') && !h.include?('.facebook.com') && !h.include?('.flurry.com') && !h.include?('.appspot.com') && !h.include?('.twitter.com') && !h.include?('.apache.org') && !h.include?('.amap') && !h.include?('.android.com') && !h.include?('.qq.com') && !h.include?('.google-analytics.com') && !h.include?('.baidu.com') 
  }
  hosts
end


def main()
  destdir = @options[:dir]
  destdir ||= apk2java(@options[:apk])   
  @logger.info "destdir is : #{destdir}"
  urls = grephttp(destdir)
  puts urls
  hosts = hosts_of_urls(urls)
  hosts = remove_bigbrother(hosts)
  puts hosts
  ips = ips_of_hosts(hosts)
  domains = domains_of_hosts(hosts)
  puts ips
end

main()
